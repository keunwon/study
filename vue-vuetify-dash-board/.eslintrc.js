module.exports = {
	root: true,
	env: {
		node: true,
	},
	extends: [
		'eslint:recommended',
		'plugin:vue/essential',
		'prettier',
		'plugin:prettier/recommended',
	],
	plugins: ['prettier'],
	rules: {
		'prettier/prettier': [
			'error',
			{
				singleQuote: true,
				semi: true,
				useTabs: false,
				tabWidth: 2,
				trailingComma: 'all',
				printWidth: 120,
				bracketSpacing: true,
				arrowParens: 'avoid',
			},
		],
		'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
	},
	parserOptions: {
		parser: "babel-eslint"
	},
	overrides: [
		{
			files: [
				'**/__tests__/*.{j,t}s?(x)',
				'**/tests/unit/**/*.spec.{j,t}s?(x)',
			],
			env: {
				jest: true,
			},
		},
	],
};
