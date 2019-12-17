node {

    stage('Build') {
        withMaven(jdk: 'default', maven: 'default') {
            sh 'mvn clean package docker:build'
        }
    }

    stage('DockerPush') {


        echo '================开始推送镜像================'
        sh """
            sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
            sudo docker tag uniskare_eureka_gateway/eureka_gateway registry.cn-shanghai.aliyuncs.com/uniskare/gateway:1.0
            sudo docker push registry.cn-shanghai.aliyuncs.com/uniskare/gateway:1.0
            """
        echo '================结束推送镜像================'

    }
    stage('RemotePull'){



        echo '================开始远程启动================'
        echo '================lk_host_server================'
        sh """
            ssh root@47.102.158.185 -tt << remotessh
            cd /usr/src/doc
            sudo docker stop gateway-container
            sudo docker rm gateway-container
            echo '========停止并删除旧的容器成功============='
            sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
            sudo docker pull registry.cn-shanghai.aliyuncs.com/uniskare/gateway:1.0
            sudo docker run -itd -p 8999:8999 --rm --network=host --name=gateway-container registry.cn-shanghai.aliyuncs.com/uniskare/gateway:1.0
            echo 'finished!'
            exit
            remotessh
        """
        echo '================lk_sever_end=============='

        echo '================eureka_gateway_success=============='
    }
}