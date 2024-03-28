pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                // Git 리포지토리 클론 단계
                checkout scm
            }
        }
        
        stage('Build and Push Image') {
            steps {
                script {
                    def kanikoImage = 'gcr.io/kaniko-project/executor:latest'
                    def dockerfilePath = "Dockerfile" // 작업 공간 내의 Dockerfile을 사용
                    def destination = "********.dkr.ecr.ap-northeast-2.amazonaws.com/teichae:${env.BUILD_NUMBER}" // 이미지 목적지를 정의합니다.

                    // 이미지 빌드 및 ECR로 푸시
                    sh "${kanikoImage} --context . --dockerfile ${dockerfilePath} --destination ${destination} --force"
                }
            }
        }
    }
}
