pipeline {
    agent any
    parameters {
        // string(name: 'VERSION', defaultValue: '', description: 'Version of the project')
        choice(name: 'VERSION', defaultValue: '', description: 'Version of the project', choices: ['1.0', '1.1', '1.2'])
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Execute tests')
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