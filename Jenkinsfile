pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', description: 'Version of the project', choices: ['1.0', '1.1', '1.2'])
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Execute tests')
    }
    stages {
        stage('build') {
            steps {
                echo "building ..."
                echo "VERSION: ${params.VERSION}"
            }
        }
        stage ('test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo "testing..."
            }
        }
        stage ('deploy') {
            steps {
                echo "deploying..."
            }
        }
    }
}