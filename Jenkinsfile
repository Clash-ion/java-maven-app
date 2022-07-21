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
                    gv.makeJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    gv.makeImage()
                }
            }
        }
        stage('deploy') {
            steps {
                echo 'deploying...'
            }
        }
    }
}
