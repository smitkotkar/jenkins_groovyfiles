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
                sh '/opt/apache-maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://172.31.31.108:9000 -Dsonar.6c2f4eb508f13bce46458baccaa055c639bcbd8e'
            }
        }
        stage ('deploy') {
            steps {
                echo 'deploy succeed'
            }
        }
    }
}