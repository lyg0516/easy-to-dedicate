node {
     stage('Clone repository') {
         checkout scm
     }

     stage('Build image') {
         #이미지 빌드시 이름을 ECR 쪽으로 변경
         app = docker.build("********.dkr.ecr.ap-northeast-2.amazonaws.com/teichae")
     }

     stage('Push image') {
         sh 'rm  ~/.dockercfg || true'
         sh 'rm ~/.docker/config.json || true'
       
         # ECR에서 생성한 Repository URI로 변경 및 Jenkins AWS Credential으로 변경
         docker.withRegistry('https://********.dkr.ecr.ap-northeast-2.amazonaws.com', 'ecr:ap-northeast-2:teichae-ecr-credentials') {
             app.push("${env.BUILD_NUMBER}")
             app.push("latest")
     }
  }
}
