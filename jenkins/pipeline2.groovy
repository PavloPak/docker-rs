pipeline {
  agent {
    label 'docker-worker-pod'  
  }
  stages {
    stage('Get project') {
      steps {
        echo 'Getting project >> >> >>'
        git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/PavloPak/zalando-ts'
      }
    }
    stage('Run tests') {
      steps {
        container('maven') {
          echo 'Starting'
          sh "ls -la"
          sh "mvn clean test"
          //sh "docker build -t ppak4dev/udemy-dmeo-server:${env.BUILD_NUMBER} ./server "
          //sh "docker build -t ppak4dev/udemy-dmeo-worker:${env.BUILD_NUMBER} ./worker "
        }
      }
    }
  }
}
