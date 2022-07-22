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
}

def incrementGitVersion() {
    withCredentials([
        usernamePassword(
            credentialsId: 'github-credentials',
            usernameVariable: 'USER',
            passwordVariable: 'PASS'
        )
    ]) {
        sh 'git config --global user.email "jenkins@jenkins.com"'
        sh 'git config --global user.name "Jenkins"'
        sh "git remote set-url origin https://${USER}:${PASS}@github.com/Clash-ion/java-maven-app.git"
        sh 'git add .'
        sh 'git commit -m "ci : increment version"'
        sh 'git push origin HEAD:jenkins-job'
    }
}

return this
