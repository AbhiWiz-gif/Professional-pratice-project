pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building the code using Maven'
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                echo 'Done Unit and integration testing using mvn test'
            }
            post {
                success {
                    mail to: 'abhii.mailboxx@gmail.com',
                    subject: 'Unit and Integration Testing',
                    body: 'Unit and integration test have succeeded. See attached logs for details.'            
                }
                failure {
                     mail to: 'abhii.mailboxx@gmail.com',
                    subject: 'Unit and Integration Testing',
                    body: 'Unit and integration test have Failed. See attached logs for details.'
                }
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Use SonarQube for code analysis'
            }
        }
        stage('Security Scan') {
            steps {
                echo 'Use OWASP ZAP for security Scan'
            }
            post {
                success {
                    mail to: 'abhii.mailboxx@gmail.com',
                    subject: 'Security scan',
                    body: 'Security scans has succecced. See attached logs for details'
                }
                failure {
                    mail to: 'abhii.mailboxx@gmail.com',
                    subject: 'Security scan',
                    body: 'Security scans has failed. See attached logs for details'
                }
            }
        }
        stage('Deploy to staging') {
            steps {
                echo 'Using AWS CLI to deploy to EC2'
            }
        }
        stage('Integration tests on staging') {
            steps {
                echo 'Using a tool like Selenium for integration tests'
            }
        }
        stage('deploy to production') {
            steps {
                echo 'Using AWS CLI to deploy'
            }
        }
    }
}
