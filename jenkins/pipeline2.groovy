pipeline {
  agent {
    label 'docker-worker-pod'
  }
  stages {
    stage('Get project') {
        echo 'Getting project >> >> >>'
        git branch: 'main', credentialsId: 'jenkins', url: 'https://github.com/PavloPak/docker-rs'
      }
  }
}
