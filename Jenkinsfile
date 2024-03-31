pipeline{
    agent {
        kubernetes{
            yaml '''
               apiVersoin: v1
               kind: Pod
               spec:
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
    stages{
        stage('Clone repository') {
            steps {
                script {
                    checkout scm
                    sh 'ls -al'
                }
            }
        }
      
        stage('docker'){
            steps{
                container('kaniko'){
                    sh "executor --dockerfile=Dockerfile --context=dir://${env.WORKSPACE} --992382830946.dkr.ecr.ap-northeast-2.amazonaws.com/easy-to-dedicate:latest"
                }
            }
        }
    }
}
