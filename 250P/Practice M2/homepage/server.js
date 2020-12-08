var http = require("http"),
    path = require("path"),
    fs = require("fs"),
    url = require("url");


var server = http.createServer(function (req, res) {
    var pathObj = url.parse(req.url)

    if (pathObj.pathname === '/') {
        pathObj.pathname += 'index.html'
    }
    var filePath = path.join(__dirname, pathObj.pathname)


    fs.readFile(filePath, function (err, fileContent) {
        if (err) {
            console.log('404\t' + req.url)
            var notFoundPage = path.join(__dirname, '404.html')
            console.log(notFoundPage)
            fs.readFile(notFoundPage, (err, notFoundContent) => {
                if (err) {
                    console.log('Error')
                    res.writeHead(404)
                    res.write("404 Not Found")
                    res.end()
                } else {
                    res.writeHead(404)
                    res.write(notFoundContent)
                    res.end()
                }
            });
        } else {
            console.log('OK\t' + req.url)
            res.writeHead(200)
            res.write(fileContent)
            res.end()
        }
    })
})


server.listen(80)
console.log("Server Listen...")