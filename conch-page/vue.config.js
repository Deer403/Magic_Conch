const path = require("path");

const CompressionWebpackPlugin = require("compression-webpack-plugin");
const zopfli = require("@gfx/zopfli");
const BrotliPlugin = require("brotli-webpack-plugin");

const IS_PROD = ["production", "prod"].includes(process.env.NODE_ENV);
const productionGzipExtensions = /\.(js|css|json|txt|html|ico|svg)(\?.*)?$/i;

function resolve(dir) {
  return path.join(__dirname, dir);
}

module.exports = {
  lintOnSave: false,
  productionSourceMap: false,
  chainWebpack: (config) => {
    config.resolve.alias
      .set("@", resolve("src"))
      .set("styles", resolve("src/assets/styles"))
      .set("common", resolve("src/common"));
    if (IS_PROD) {
      config.module
        .rule("images")
        .use("image-webpack-loader")
        .loader("image-webpack-loader")
        .options({
          mozjpeg: { progressive: true, quality: 65 },
          optipng: { enabled: false },
          pngquant: { quality: [0.65, 0.9], speed: 4 },
          gifsicle: { interlaced: false },
          // webp: { quality: 75 }
        });
    }
    const cdn = {
      // 访问https://unpkg.com/element-ui/lib/theme-chalk/index.css获取最新版本
      css: ["//unpkg.com/element-ui@2.10.1/lib/theme-chalk/index.css"],
      js: [
        "//unpkg.com/vue@2.6.10/dist/vue.min.js", // 访问https://unpkg.com/vue/dist/vue.min.js获取最新版本
        "//unpkg.com/vue-router@3.0.6/dist/vue-router.min.js",
        "//unpkg.com/vuex@3.1.1/dist/vuex.min.js",
        "//unpkg.com/axios@0.19.0/dist/axios.min.js",
        "//unpkg.com/element-ui@2.10.1/lib/index.js",
      ],
    };

    // 如果使用多页面打包，使用vue inspect --plugins查看html是否在结果数组中
    config.plugin("html").tap((args) => {
      // html中添加cdn
      args[0].cdn = cdn;
      return args;
    });
  },
  configureWebpack: (config) => {
    config.externals = {
      vue: "Vue",
      "element-ui": "ELEMENT",
      "vue-router": "VueRouter",
      vuex: "Vuex",
      axios: "axios",
    };
    const plugins = [];
    if (IS_PROD) {
      plugins.push(
        new CompressionWebpackPlugin({
          filename: "[path].gz[query]",
          algorithm: "gzip",
          test: productionGzipExtensions,
          threshold: 10240,
          minRatio: 0.8,
        })
      );

      config.optimization = {
        splitChunks: {
          cacheGroups: {
            common: {
              name: "chunk-common",
              chunks: "initial",
              minChunks: 2,
              maxInitialRequests: 5,
              minSize: 0,
              priority: 1,
              reuseExistingChunk: true,
              enforce: true,
            },
            vendors: {
              name: "chunk-vendors",
              test: /[\\/]node_modules[\\/]/,
              chunks: "initial",
              priority: 2,
              reuseExistingChunk: true,
              enforce: true,
            },
            elementUI: {
              name: "chunk-elementui",
              test: /[\\/]node_modules[\\/]element-ui[\\/]/,
              chunks: "all",
              priority: 3,
              reuseExistingChunk: true,
              enforce: true,
            },
            echarts: {
              name: "chunk-echarts",
              test: /[\\/]node_modules[\\/](vue-)?echarts[\\/]/,
              chunks: "all",
              priority: 4,
              reuseExistingChunk: true,
              enforce: true,
            },
          },
        },
      };
    }
    config.plugins = [...config.plugins, ...plugins];
  },
  devServer: {
    proxy: {
      "/api": {
        target: "http://120.79.189.120:8090/",
        changeOrigin: true, // needed for virtual hosted sites
        ws: true, // proxy websockets
        pathRewrite: {
          "^/api": "",
        },
      },
    },
  },
};
