pipeline {
    agent any
    stages {
        stage ('pull') {
            steps {
                git branch: 'main', url: 'https://github.com/smitkotkar/studentapp-ui.git'
            }
        }
        stage ('build') {
            steps {
                sh '/opt/apache-maven/bin/mvn clean package'
            }
        }
        stage ('test') {
            steps {
                withSonarQubeEnv(installationName:'sonar-server',credentialsId: 'sonar-token') { 
                  sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.ProjectKey=student-app'
                }  
            }
        }
        stage ('quality-gate') {
            steps {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('deploy') {
            steps {
                echo 'deploy succeed'
            }
        }
    }
}