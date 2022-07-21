pipeline {
    agent any
    parameters {
        choice(name: 'VERSION', defaultValue: '', description: 'Version of the project', choices: ['1.0', '1.1', '1.2'])
        booleanParameters(name: 'executeTests', defaultValue: true, description: 'Execute tests')
    }
    stages {
        stage('build') {
            steps {
                echo "building ..."
                echo "VERSION: ${parameters.VERSION}"
            }
        }
        stage ('test') {
            when {
                expression {
                    parameters.executeTests
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