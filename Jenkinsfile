node {
    stage('SCM') {
        git 'https://github.com/wzhkun/Software-Project-Risk-Management-System'
    }
    stage('QA') {
        sh 'sonar-scanner'
    }
    stage('build') {
        def mvnHome = tool 'Maven'
        sh "${mvnHome}/bin/mvn -B clean package"
        sh "docker tag wzhkun/sprms wzhkun/sprms"
        sh "${mvnHome}/bin/mvn package docker:build"
    }
    stage('deploy') {
        sh "docker stop sprms || true"
        sh "docker rm sprms || true"
        sh "docker run --name sprms -p 10080:10080 -d wzhkun/sprms"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
}
