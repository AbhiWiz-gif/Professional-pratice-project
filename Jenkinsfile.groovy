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
                echo 'Done Unit and integrations testing using mvn test'
            }
            post {
                success {
                        to: 'abhii.mailboxx@gmail.com',
                        subject: 'Unit and Integration Testing',
                        body: 'Unit and integration test have succeeded. See attached logs for details.',
                        attachmentsPattern: '**/target/surefire-reports/*.txt'
                }
                failure {
                        to: 'abhii.mailboxx@gmail.com',
                        subject: 'Unit and Integration Testing',
                        body: 'Unit and integration test have Failed. See attached logs for details.',
                        attachmentsPattern: '**/target/surefire-reports/*.txt'
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
                echo 'Use OWASPs ZAP for security Scan'
            }
            post {
                success {
                    emailext (
                        to: 'abhii.mailboxx@gmail.com',
                        subject: 'Security scan',
                        body: 'Security scans have succeeded. See attached logs for details.',
                        attachmentsPattern: '**/target/security-reports/*.txt'
                    )
                }
                failure {
                    emailext (
                        to: 'abhii.mailboxx@gmail.com',
                        subject: 'Security scan',
                        body: 'Security scans have failed. See attached logs for details.',
                        attachmentsPattern: '**/target/security-reports/*.txt'
                    )
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
        stage('Deploy to production') {
            steps {
                echo 'Using AWS CLI to deploy'
            }
        }
    }
}
