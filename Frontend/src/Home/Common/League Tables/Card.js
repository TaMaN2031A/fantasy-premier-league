import React from 'react';

function Card(props) {
	return (
		<div className="flex flex-col items-center justify-center px-6 py-2 mx-auto md:h-screen lg:py-0">
			<div className="mx-auto max-w-screen-xl px-4 lg:px-12">
				<div className="relative overflow-x-auto">
					<table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
						<thead
							className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
						<tr>
							<th scope="col" className="px-6 py-3">
								name
							</th>
							<th scope="col" className="px-6 py-3">
								{props.criteria}
							</th>
						</tr>
						</thead>
						<tbody>
						{
							props.data.map((item, index) => (
								<tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
									<td className="px-6 py-4">
										{item.name}
									</td>
									<td className="px-6 py-4">
										{item.value}
									</td>
								</tr>
							))
						}
						</tbody>
					</table>
				</div>
			</div>
		</div>
	);
}

export default Card;