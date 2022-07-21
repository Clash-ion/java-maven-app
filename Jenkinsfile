pipeline {
    agent any
    environment {
        NEW_VERSION = '1.0.0'
    }
    stages {
        stage('build') {
            steps {
                echo "building ..."
                echo "building ${NEW_VERSION}"
            }
        }
        stage ('test') {
            steps {
                echo "testing..."
            }
        }
        stage ('deploy') {
            steps {
                echo "deploying..."
                withCredentials([
                    usernamePassword(credentials: "nexus-docker-repo", usernameVariable: "NEXUSUSERNAME", passwordVariable: "NEXUSPASSWORD" )
                ]) {
                    sh "echo ${NEXUSUSERNAME} ${NEXUSPASSWORD}"
                }
            }
        }
    }
}