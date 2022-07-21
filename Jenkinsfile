
def gv

pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', description: 'Version of the project', choices: ['1.0', '1.1', '1.2'])
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Execute tests')
    }
    stages {
        stage ('init') {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }
        stage ('test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage ('deploy') {
            input {
                message "Select the environment to deploy to"
                ok "Done"
                parameters {
                    choice( name: 'environment', description: 'Environment to deploy to', choices: ['dev', 'staging', 'prod'] )
                }
            }
            steps {
                script {
                    gv.deployApp()
                    echo "Deployed to ${params.enviroment}"
                }
            }
        }
    }
}