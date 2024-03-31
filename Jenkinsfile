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
                     - name: registry-credentials
                       mountPath: /kaniko/.docker
                 volumes:
                 - name: registry-credentials
                   secret:
                     secretName: my-reg
                     items:
                     - key: .dockerconfigjson
                       path: config.json
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
                container(name: 'kaniko') {
                    sh"/kaniko/executor --dockerfile=Dockerfile \
                    --context=dir://${env.WORKSPACE} \
                    --insecure \
                    --skip-tls-verify  \
                    --cleanup \
                    --verbosity debug \
                    --destination=992382830946.dkr.ecr.ap-northeast-2.amazonaws.com/easy-to-dedicate:latest"
                }
            }
        }
    }
}
