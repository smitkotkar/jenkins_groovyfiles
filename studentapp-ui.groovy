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
                sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://172.31.22.135:9000 -Dsonar.e536055f5f6bcc483234a017838f922527741f12'
            }
        }
        stage ('deploy') {
            steps {
                echo 'deploy succeed'
            }
        }
    }
}