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
        stage('increment version') {
            steps {
                script {
                    echo 'incrementing version ...'
                    gv.incrementVersion()
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
            when {
                expression {
                    env.BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
        stage('increment git version') {
            steps {
                script {
                    echo 'incrementing git version test ...'
                    gv.incrementGitVersion()
                }
            }
        }
    }
}
