pipeline {
    agent {
        kubernetes {
            yaml '''
               apiVersion: v1
               kind: Pod
               spec:
                 nodeSelector:
                   kubernetes.io/hostname: ip-10-0-1-217.ap-northeast-2.compute.internal
                 containers:
                 - name: kaniko
                   image: gcr.io/kaniko-project/executor:debug
                   command:
                   - sleep
                   args:
                   - infinity
                   volumeMounts:
                     - name: docker-config
                       mountPath: /kaniko/.docker
                 volumes:
                   - name: docker-config
                     configMap:
                       name: docker-config
            '''
        }
    }
    stages {
        stage('Clone repository') {
            steps {
                script {
                    checkout scm
                }
            }
        }
      
        stage('build and push') {
            steps {
                container(name: 'kaniko') { //ecr repo는 테스트 후에 환경변수로 변경해 안보이게 할 예정
                    sh "/kaniko/executor --context . --dockerfile=Dockerfile --destination=992382830946.dkr.ecr.ap-northeast-2.amazonaws.com/easy-to-dedicate:latest"
                }
            }
        }
    }
}
