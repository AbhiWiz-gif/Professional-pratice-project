pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Builded the code using Maven'
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                echo 'Done Unit and integration testing using mvn test'
            }
            post {
                success {
                    emailext subject: 'Unit and Integration Tests Success',
                    body: 'Unit and integration tests have succeeded. See attached logs for details.',
                    to: 'abhii.mailboxx@gmail.com',
                    attachLog: true
                }
                failure {
                    emailext subject: 'Unit and Integration Tests Failure',
                    body: 'Unit and integration tests have failed. See attached logs for details.',
                    to: 'abhii.mailboxx@gmail.com',
                    attachLog: true
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
                    emailext subject: 'Security Scan Success',
                    body: 'Security scan has succeeded. See attached logs for details.',
                    to: 'abhii.mailboxx@gmail.com',
                    attachLog: true
                }
                failure {
                    emailext subject: 'Security Scan Failure',
                    body: 'Security scan has failed. See attached logs for details.',
                    to: 'abhii.mailboxx@gmail.com',
                    attachLog: true
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
