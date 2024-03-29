podTemplate(yaml: '''
              kind: Pod
              metadata:
                name: kaniko-image-build-pod
              spec:
                containers:
                - name: kaniko
                  image: gcr.io/kaniko-project/executor:v1.6.0-debug
                  imagePullPolicy: Always
                  command:
                  - sleep
                  args:
                  - 99d
'''
  )

node {
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
                    sh 'ls -la'
                }
            }
        }

        stage('Build and Push Image') {
            steps {
                script {
                    def dockerfilePath = "Dockerfile" // 작업 공간 내의 Dockerfile을 사용
                    def destination = "${REGISTRY_URL}:${IMAGE_TAG}" // ECR 이미지 목적지
                    
                    // 이미지 빌드 및 푸시 작업 수행
                    container('kaniko') {
                    sh "/kaniko/executor --context . \
                        --dockerfile ${dockerfilePath} \
                        --destination ${destination} \
                        --skip-tls-verify \
                        --dockerconfig ${CREDENTIAL_ID}"
                    }
                }
            }
        }
    }
}
