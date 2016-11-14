node {
    stage('SCM') {
        git 'https://github.com/wzhkun/Software-Project-Risk-Management-System'
    }
    stage('QA') {
        cmd 'sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'M3'
        cmd "${mvnHome}/bin/mvn -B clean package"
        cmd "${mvnHome}/bin/mvn package docker:build -DpushImage"
    }
    stage('deploy') {
        cmd "docker stop my || true"
        cmd "docker rm my || true"
        cmd "docker load </target/docker"
        cmd "docker run -p 10080:10080 -t sprms/sprms"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
