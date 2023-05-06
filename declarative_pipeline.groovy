pipeline {
    agent any
    stages {
        stage ('pull') {
            steps {
                echo 'pull succeed'  
                
            }
        }
        stage ('build') {
            steps {
                echo 'congrats! build is done'
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