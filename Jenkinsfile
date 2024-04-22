pipeline {
    agent {
        kubernetes {
            yaml '''
               apiVersion: v1
               kind: Pod
               spec:
                 nodeSelector:
                   kubernetes.io/hostname: gke-etd-default-pool-2f5cd461-whht
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
                    --destination=${ECR_REPO}:${BUILD_NUMBER} \
                    --destination=${ECR_REPO}:latest
                    '''
                }
            }
        }
        
        stage('Update Manifest') {
            steps {
                git branch: 'main', credentialsId: 'ickyc', url: 'https://github.com/easy-to-dedicate/app.git' 
                    
                    sh "git config --global user.email yechan0329@gmail.com"
                    sh "git config --global user.name ickyc"
                    sh "sed -i 's/easy-to-dedicate:.*\$/easy-to-dedicate:${BUILD_NUMBER}/g' deployment.yaml"
                    sh "git add ."
                    sh "git commit -m '[UPDATE] easy-to-dedicate ${BUILD_NUMBER} image versioning'"
                    
                sshagent(credentials: ['ickyc-ssh']) {
                    sh '''
                    export GIT_SSH_COMMAND="ssh -oStrictHostKeyChecking=no"
                    git remote set-url origin git@github.com:easy-to-dedicate/app.git
                    git push -u origin main
                    '''
                 }
            }
        }
    }
}
