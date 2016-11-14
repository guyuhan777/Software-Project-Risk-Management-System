node {
    stage('SCM') {
        git 'https://github.com/wzhkun/Software-Project-Risk-Management-System'
    }
    stage('QA') {
        bat 'sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        bat "${mvnHome}/bin/mvn -B clean package"
        bat "${mvnHome}/bin/mvn package docker:build -DpushImage"
    }
    stage('deploy') {
        bat "docker stop my || true"
        bat "docker rm my || true"
        bat "docker load </target/docker"
        bat "docker run -p 10080:10080 -t wzhkun/sprms"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
}
