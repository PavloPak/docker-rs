pipeline {
  agent {
    label 'docker-worker-pod'  
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
          container('docker') {
            sh "docker build -t ppak4dev/udemy-dmeo-client:${env.BUILD_NUMBER} ./client "
            // sh "docker build -t ppak4dev/udemy-dmeo-nginx:${env.BUILD_NUMBER} ./nginx "
            sh "docker build -t ppak4dev/udemy-dmeo-server:${env.BUILD_NUMBER} ./server "
            sh "docker build -t ppak4dev/udemy-dmeo-worker:${env.BUILD_NUMBER} ./worker "
          }
         }
       }
    stage('Push image') {
        steps {
          withCredentials([usernamePassword(credentialsId: 'Docker-Hub-U-P', passwordVariable: 'DOCKER_REGISTRY_PWD', usernameVariable: 'DOCKER_REGISTRY_USER')]) { 
          container('docker') {
            sh '''
            set +x
            if [ ${PUSH_IMAGE} == "true" ]; then
               echo "$DOCKER_REGISTRY_PWD" | docker login -u "$DOCKER_REGISTRY_USER" --password-stdin                              
               echo Login Completed    
               docker push ppak4dev/udemy-dmeo-client:"${BUILD_NUMBER}"
               // docker push ppak4dev/udemy-dmeo-nginx:"${BUILD_NUMBER}"
               docker push ppak4dev/udemy-dmeo-server:"${BUILD_NUMBER}"
               docker push ppak4dev/udemy-dmeo-worker:"${BUILD_NUMBER}"
            fi;   
          '''
         }
       }  
      }
    }
  }
}
