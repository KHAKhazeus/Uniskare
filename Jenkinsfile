node {

    stage('Preparation') {
            checkout([$class: 'GitSCM', branches: [[name: '*/user_dev']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'uniskare_remote_server', url: 'https://github.com/KHAKhazeus/Uniskare/']]])
        }

    stage('Build') {
        withMaven(jdk: 'default', maven: 'default') {
            sh 'mvn clean package docker:build'
        }
    }

    stage('DockerPush') {


        echo '================开始推送镜像================'
        sh """
            sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
            sudo docker tag uniskare_eureka_user/eureka_user registry.cn-shanghai.aliyuncs.com/uniskare/user:2.0
            sudo docker push registry.cn-shanghai.aliyuncs.com/uniskare/user:2.0
            """
        echo '================结束推送镜像================'

    }
    stage('RemotePull'){



        echo '================开始远程启动================'
        echo '================lk_server================'
        sh """
            ssh ubuntu@49.235.255.145 -tt << remotessh
            cd /usr/src/doc
            sudo docker stop user-container
            sudo docker rm user-container
            echo '========停止并删除旧的容器成功============='
            sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
            sudo docker pull registry.cn-shanghai.aliyuncs.com/uniskare/user:2.0
            sudo docker run -itd -p 8928:8928 --rm --network=host --name=user-container registry.cn-shanghai.aliyuncs.com/uniskare/user:2.0 --spring.profiles.active=peer1
            echo 'finished!'
            exit
            remotessh
           """
        echo '================lk_sever_end=============='

    //    echo '================bai_remote_server================'
    //    sh """
    //        ssh ubuntu@49.234.96.26 -tt << remotessh
    //        cd /usr/src/doc
    //        sudo docker stop user-container
    //        sudo docker rm user-container
    //        echo '========停止并删除旧的容器成功============='
    //        sudo docker login --username=柠檬一起啊啊啊啊啊 --password=woshi123 registry.cn-shanghai.aliyuncs.com
    //        sudo docker pull registry.cn-shanghai.aliyuncs.com/uniskare/user:2.0
    //        sudo docker run -itd -p 8928:8928 --rm --network=host --name=user-container registry.cn-shanghai.aliyuncs.com/uniskare/user:2.0 --spring.profiles.active=peer2
    //        echo 'finished!'
    //        exit
    //        remotessh
     //   """
     //   echo '================bai_remote_end=============='

        echo '================eureka_user_success=============='
    }
}