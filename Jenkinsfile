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
                   tty: true
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
    environment {
        ECR_REPO = credentials('ecr-repo')
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
                container(name: 'kaniko', shell: '/busybox/sh') { //ecr repo는 테스트 후에 환경변수로 변경해 안보이게 할 예정
                    sh '''#!/busybox/sh
                    echo "in kaniko" 
                    /kaniko/executor --context `pwd` --dockerfile Dockerfile --verbosity debug --destination=${ECR_REPO}:${env.BUILD_NUMBER}
                    '''
                }
            }
        }
    }
}
