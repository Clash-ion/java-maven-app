library identifier: "jenkins-shared-library@master", retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/Clash-ion/jenkins-shared-library.git',
    credentialsId: 'github-credentials']
)
// @Library('jenkins-shared-library')

def gv

pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    stages {
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }
        stage('build jar') {
            steps {
                script {
                    makeJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    makeImage 'clashia/java-maven-app:jma-3.0'
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
