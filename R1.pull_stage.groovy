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
                echo 'build succeed'
            }
        }
        stage ('test') {
            steps {
                echo 'test succeed'
            }
        }
        stage ('deploy') {
            steps {
                echo 'deploy succeed'
            }
        }
    }
}