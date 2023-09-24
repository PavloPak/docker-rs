pipeline {
  agent {
    kubernetes {
      inheritFrom 'docker-worker-pod'
    }
  }
  stages {
    stage('Get project') {
      steps {
        echo 'Getting project >> >> >>'
        git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/PavloPak/docker-rs'
      }
    }
    stage('Build Docker image') {
      steps {
        echo 'Hellooo ! 4  !'
        sh "docker build -t ppak4dev/udemy-dmeo-client:${env.BUILD_NUMBER} ./client "
        sh "docker build -t ppak4dev/udemy-dmeo-nginx:${env.BUILD_NUMBER} ./nginx "
        sh "docker build -t ppak4dev/udemy-dmeo-server:${env.BUILD_NUMBER} ./server "
        sh "docker build -t ppak4dev/udemy-dmeo-worker:${env.BUILD_NUMBER} ./worker "
      }
    }
  }
}
