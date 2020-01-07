node {

    stage('Preparation') {
            checkout([$class: 'GitSCM', branches: [[name: '*/order_dev_2']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'uniskare_remote_server', url: 'https://github.com/KHAKhazeus/Uniskare/']]])
        }

    stage('Build') {
        withMaven(jdk: 'default', maven: 'default') {
            sh 'mvn clean package deploy docker:build'
        }
    }

    stage('DockerPush') {


        echo '================开始推送镜像================'
        sh """
            sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
            sudo docker tag uniskare_eureka_order/eureka_order registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0
            sudo docker push registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0
            """
        echo '================结束推送镜像================'

    }
    stage('RemotePull'){



        echo '================开始远程启动================'
        echo '================sch_host_server================'
        sh """
            ssh root@121.199.0.20 -tt << remotessh
            cd /usr/src/doc
            sudo docker stop order-container-1

            sudo docker rm order-container-1

            echo '========停止并删除旧的容器成功============='
            sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
            sudo docker pull registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0
            sudo docker run -itd -p 8909:8909 --rm --network=host --name=order-container-1 registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0 --spring.profiles.active=peer1
            echo 'finished!'
            exit
            remotessh
        """
        echo '================sch_sever_end=============='

        echo '================lk_host_server================'
            sh """
                ssh ubuntu@49.235.255.145 -tt << remotessh
                cd /usr/src/doc
                sudo docker stop order-container-1

                sudo docker rm order-container-1

                echo '========停止并删除旧的容器成功============='
                sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
                sudo docker pull registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0
                sudo docker run -itd -p 8910:8910 --rm --network=host --name=order-container-1 registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0 --spring.profiles.active=peer2
                echo 'finished!'
                exit
                remotessh
            """
        echo '================lk_sever_end=============='


        echo '================lk_host_server================'
            sh """
                ssh ubuntu@49.234.96.26 -tt << remotessh
                cd /usr/src/doc
                sudo docker stop order-container-1

                sudo docker rm order-container-1

                echo '========停止并删除旧的容器成功============='
                sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
                sudo docker pull registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0
                sudo docker run -itd -p 8911:8911 --rm --network=host --name=order-container-1 registry.cn-shanghai.aliyuncs.com/uniskare/order:1.0 --spring.profiles.active=peer3
                echo 'finished!'
                exit
                remotessh
            """
        echo '================lk_sever_end=============='


        echo '================eureka_order_success=============='
    }
}