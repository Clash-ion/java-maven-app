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
                    usernamePassword(credentialsId : 'nexus-docker-repo', usernameVariable: 'USER', passwordVariable: 'PASS' )
                ]) {
                    sh "echo ${USER} ${PASS}"
                }
            }
        }
    }
}