pipeline {
    agent {
        kubernetes {
            yaml '''
               apiVersion: v1
               kind: Pod
               spec:
                 nodeSelector:
                   kubernetes.io/hostname: ${HOSTNAME}
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
    stages {
        stage('Clone repository') {
            steps {
                script {
                    checkout scm
                }
            }
        }
      
        stage('build and push') {
                container(name: 'kaniko', shell: '/busybox/sh') { //ecr repo는 테스트 후에 환경변수로 변경해 안보이게 할 예정
                    sh '''#!/busybox/sh
                    echo "in kaniko" 
                    /kaniko/executor --context `pwd` --dockerfile Dockerfile --verbosity debug --${ECR-REPO}:${env.BUILD_NUMBER}
                    '''
                }
            }
        }
    }
}
