def incrementVersion() {
    sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}

def makeJar() {
    echo 'building jar file ...'
    sh 'mvn clean package'
}

def makeImage() {
    echo 'building image through webhook ...'
    withCredentials([
        usernamePassword(
            credentialsId: 'docker-hub',
            usernameVariable: 'USER',
            passwordVariable: 'PASS'
        )
    ]) {
        sh "docker build -t  clashia/java-maven-app:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push clashia/java-maven-app:${IMAGE_NAME}"
    }
} // end withCredentials

def deployApp() {
    echo 'deploying...'
    def commands = [
        "docker pull clashia/java-maven-app:${IMAGE_NAME}",
        'docker stop java-maven-app',
        'docker rm java-maven-app',
        // from container port 8080 to container port 5000
        "docker run -d -p 5000:8080 --name java-maven-app clashia/java-maven-app:${IMAGE_NAME}"
    // "docker run -d --name java-maven-app -p 8080:8080 clashia/java-maven-app:${IMAGE_NAME}"
    ]
    sshagent(['ec2-server-key']) {
        sh 'ssh -o StrictHostKeyChecking=no ec2-user@3.93.172.181 '
        sh commands.join(' && ')
    }
}

def incrementGitVersion() {
    withCredentials([
        usernamePassword(
            credentialsId: 'github-encoded-creds',
            usernameVariable: 'USERNAME',
            passwordVariable: 'PASSWORD'
        )
    ]) {
        sh 'git config --global user.email "jenkins@jenkins.com"'
        sh 'git config --global user.name "Jenkins"'
        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/Clash-ion/java-maven-app.git"
        sh 'git add .'
        sh 'git commit -m "ci : increment version"'
        sh 'git push origin HEAD:jenkins-job'
    }
}

return this
