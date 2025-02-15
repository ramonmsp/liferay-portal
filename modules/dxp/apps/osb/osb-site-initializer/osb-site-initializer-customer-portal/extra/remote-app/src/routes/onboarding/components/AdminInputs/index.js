import {useLazyQuery} from '@apollo/client';
import ClayForm, {ClayInput} from '@clayui/form';
import {useEffect, useState} from 'react';
import Input from '../../../../common/components/Input';
import useDebounce from '../../../../common/hooks/useDebounce';
import {getBannedEmailDomains} from '../../../../common/services/liferay/graphql/queries';
import {isValidEmail} from '../../../../common/utils/validations.form';

const AdminInputs = ({admin, id}) => {
	const debouncedEmail = useDebounce(admin?.email, 500);
	const [bannedDomain, setBannedDomain] = useState(debouncedEmail);

	const [fetchBannedDomain, {data}] = useLazyQuery(getBannedEmailDomains);
	const bannedDomainsItems = data?.c?.bannedEmailDomains?.items;

	useEffect(() => {
		const emailDomain = debouncedEmail.split('@')[1];

		if (emailDomain) {
			fetchBannedDomain({
				variables: {
					filter: `domain eq '${emailDomain}'`,
				},
			});

			if (bannedDomainsItems?.length) {
				setBannedDomain(bannedDomainsItems[0].domain);
			}
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [bannedDomainsItems, debouncedEmail]);

	return (
		<ClayForm.Group className="mb-0 pb-1">
			<hr className="mb-4 mt-4 mx-3" />

			<Input
				groupStyle="pt-1"
				label="DXP Cloud System Admin's Email Address"
				name={`dxp.admins[${id}].email`}
				placeholder="email@example.com"
				required
				type="email"
				validations={[(value) => isValidEmail(value, bannedDomain)]}
			/>

			<ClayInput.Group className="mb-0">
				<ClayInput.GroupItem className="m-0">
					<Input
						label="System Admin’s First Name"
						name={`dxp.admins[${id}].firstName`}
						required
						type="text"
					/>
				</ClayInput.GroupItem>

				<ClayInput.GroupItem className="m-0">
					<Input
						label="System Admin’s Last Name"
						name={`dxp.admins[${id}].lastName`}
						required
						type="text"
					/>
				</ClayInput.GroupItem>
			</ClayInput.Group>

			<Input
				groupStyle="mb-0"
				label="System Admin’s Github Username"
				name={`dxp.admins[${id}].github`}
				required
				type="text"
			/>
		</ClayForm.Group>
	);
};

export default AdminInputs;
