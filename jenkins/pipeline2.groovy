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
  }
}
