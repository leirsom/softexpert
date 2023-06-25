const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/calculate', // O caminho que você deseja redirecionar para o servidor backend
    createProxyMiddleware({
      target: 'http://localhost:8080', // URL do servidor backend
      changeOrigin: true,
    })
  );
};
