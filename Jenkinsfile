node {
     environment {
        REGISTRY_URL = '992382830946.dkr.ecr.ap-northeast-2.amazonaws.com/easy-to-dedicate' // REGISTRY 주소 
        CREDENTIAL_ID = 'awsAccessKey' // Jenkins에 셋팅한 AWS용 Credential ID
        IMAGE_TAG = 'latest' // 이미지 태그
    }

          stage('Clone repository') {
              checkout scm
          }

}
