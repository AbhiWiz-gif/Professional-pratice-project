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
                    emailext subject: 'Pipeline Success',
        body: 'Your pipeline has succeeded. See attached logs for details.',
        to: 'abhii.mailboxx@gmail.com',
        attachLog: true
                }
                failure {
                    emailext subject: 'Pipeline Failure',
        body: 'Your pipeline has failed. See attached logs for details.',
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
