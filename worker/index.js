const keys = require('./keys');
const redis = require('redis');

const redisClient = redis.createClient({
  host: keys.redisHost,
  port: keys.redisPort,
  retry_strategy: () => 1000,
});
const sub = redisClient.duplicate();

function fibb(index) {
  function calc(val, prev, idx) {
    var newVal = val + prev;
    if (--idx == 0) return newVal;
     return calc(newVal, val, idx);
  }
  return calc(1, 0, index);
}

sub.on('message', (channel, message) => {
  redisClient.hset('values', message, fibb(parseInt(message)));
});
sub.subscribe('insert');

