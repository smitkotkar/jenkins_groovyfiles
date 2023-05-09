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
                sh '/opt/apache-maven/bin/mvn sonar:sonar mvn sonar:sonar   -Dsonar.projectKey=studentapp   -Dsonar.host.url=http://54.176.113.204:9000   -Dsonar.loe5d6200d66cef19d9d0f9a6cc1f2f4'
            }
        }
        stage ('deploy') {
            steps {
                echo 'deploy succeed'
            }
        }
    }
}