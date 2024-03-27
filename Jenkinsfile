pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                // Git 리포지토리 클론 단계
                checkout scm
            }
        }
        
        stage('Build Image') {
            steps {
                // Kaniko를 사용하여 이미지 빌드 단계
                script {
                    def kanikoImage = 'gcr.io/kaniko-project/executor:latest'
                    def dockerfilePath = 'Dockerfile' // root에 위치한 Dockerfile 참조
                    def destination = "********.dkr.ecr.ap-northeast-2.amazonaws.com/teichae:${env.BUILD_NUMBER}" // 이미지 목적지를 정의합니다.

                    sh "${kanikoImage} --context . --dockerfile ${dockerfilePath} --destination ${destination} --force"
                }
            }
        }
        
        stage('Push Image to ECR') {
            steps {
                // 이미지를 ECR로 푸시하는 단계
                script {
                    def kanikoImage = 'gcr.io/kaniko-project/executor:latest'
                    def destination = "********.dkr.ecr.ap-northeast-2.amazonaws.com/teichae:${env.BUILD_NUMBER}" // 이미지 목적지를 정의합니다.

                    sh "${kanikoImage} --context . --dockerfile ./Dockerfile --destination ${destination} --force"
                }
            }
        }
    }
}
