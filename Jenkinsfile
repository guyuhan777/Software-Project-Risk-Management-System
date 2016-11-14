node {
    stage('SCM') {
        git 'https://github.com/wzhkun/Software-Project-Risk-Management-System'
    }
    stage('QA') {
        bat 'sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'Maven'
        bat "${mvnHome}/bin/mvn -B clean package"
        bat "docker tag wzhkun/sprms wzhkun/sprms"
        bat "${mvnHome}/bin/mvn package docker:build"
    }
    stage('deploy') {
        bat "docker stop my | echo true"
        bat "docker rm my | echo true"
        bat "docker load </target/docker"
        bat "docker run -p 10080:10080 -t wzhkun/sprms"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
}
