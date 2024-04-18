pipeline {
    agent {
        kubernetes {
            yaml '''
               apiVersion: v1
               kind: Pod
               spec:
                 nodeSelector:
                   kubernetes.io/hostname: gke-etd-default-pool-82fcc1d5-q2rj
                 containers:
                 - name: kaniko
                   image: gcr.io/kaniko-project/executor:debug
                   tty: true
                   command:
                   - sleep
                   args:
                   - infinity
                   volumeMounts:
                     - name: aws-secret
                       mountPath: /root/.aws
                 restartPolicy: Never
                 volumes:
                   - name: aws-secret
                     secret:
                       secretName: aws-secret
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
                container(name: 'kaniko', shell: '/busybox/sh') {
                    sh '''#!/busybox/sh
                    echo "in kaniko" 
                    /kaniko/executor --context `pwd` \
                    --dockerfile Dockerfile \
                    --verbosity debug \
                    --destination=${ECR_REPO}:"${env.BUILD_NUMBER}" \
                    --destination=${ECR_REPO}:latest
                    '''
                }
            }
        }
    }
}
