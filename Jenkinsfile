node {
     environment {
        REGISTRY_URL = '992382830946.dkr.ecr.ap-northeast-2.amazonaws.com/easy-to-dedicate' // REGISTRY 주소 
        CREDENTIAL_ID = 'awsAccessKey' // Jenkins에 셋팅한 AWS용 Credential ID
        IMAGE_TAG = 'latest' // 이미지 태그
    }
     
     stage('Clone repository') {
         checkout scm
     }
     
     stage('Build and Push Image') {
          steps {
               script {
                    def kanikoImage = 'gcr.io/kaniko-project/executor:latest'
                    def dockerfilePath = "Dockerfile" // 작업 공간 내의 Dockerfile을 사용
                    def destination = "${REGISTRY_URL}:${IMAGE_TAG}" // ECR 이미지 목적지

                    // 이미지 빌드 및 ECR로 푸시
                    sh "${kanikoImage} --context . --dockerfile ${dockerfilePath} --destination ${destination} --skip-tls-verify --dockerconfig ${CREDENTIAL_ID}"
            }
        }
}
