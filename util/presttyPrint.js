#!/usr/bin/env node

let input = '';

process.stdin.setEncoding('utf8');

process.stdin.on('readable', () => {
  let chunk;
  while ((chunk = process.stdin.read()) !== null) {
    input += chunk;
  }
});

process.stdin.on('end', () => {
  try {
    const parsed = JSON.parse(input);
    const pretty = JSON.stringify(parsed, null, 2);
    process.stdout.write(pretty + '\n');
  } catch (error) {
    process.stderr.write('Error parsing JSON: ' + error.message + '\n');
    process.exit(1);
  }
});
