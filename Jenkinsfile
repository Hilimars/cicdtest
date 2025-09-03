pipeline {
    agent any

    tools {
        maven 'Default'
        jdk 'Default'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/yourname/java-selenium-test.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Test Results') {
            steps {
                publishTestResults testResults: '**/target/surefire-reports/*.xml', format: 'TESTNG'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/surefire-reports/*.txt', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            mail to: 'team@example.com', subject: "Test Result - ${currentBuild.fullDisplayName}", body: "Build: ${currentBuild.fullDisplayName} \n Result: ${currentBuild.result}"
        }
        success {
            echo 'Tests passed!'
        }
        failure {
            echo 'Tests failed!'
        }
    }
}