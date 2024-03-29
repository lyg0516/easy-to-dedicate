pipeline {
  agent {
    kubernetes {
yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    command: ['sleep']
    args: ['infinity']
'''
    }
  }
  
    environment {
        REGISTRY_URL = '992382830946.dkr.ecr.ap-northeast-2.amazonaws.com/easy-to-dedicate' // REGISTRY 주소 
        CREDENTIAL_ID = credentials('awsAccessKey') // Jenkins에 셋팅한 AWS용 Credential ID
        IMAGE_TAG = 'latest' // 이미지 태그
    }

    stages {
        stage('Clone repository') {
            steps {
                script {
                    checkout scm
                    sh 'ls -al'
                }
            }
        }
    }
}
